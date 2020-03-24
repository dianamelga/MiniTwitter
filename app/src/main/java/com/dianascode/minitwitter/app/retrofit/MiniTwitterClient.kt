package com.dianascode.minitwitter.app.retrofit

import com.dianascode.minitwitter.app.common.Constantes.Companion.API_MINITWITTER_BASE_URL
import com.dianascode.minitwitter.app.common.Constantes.Companion.PREF_AUTH_RESPONSE
import com.dianascode.minitwitter.app.common.MiniTwitter
import com.dianascode.minitwitter.app.common.SharedPreferencesManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by Diana Melgarejo on 3/19/2020.
 */
class MiniTwitterClient private constructor(okHttpClient: OkHttpClient) {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_MINITWITTER_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val miniTwitterService: MiniTwitterService = retrofit.create(MiniTwitterService::class.java)

    companion object {
        private var mInstance: MiniTwitterClient? = null
        
        fun getInstance(): MiniTwitterClient {
            //si es la primera vez que solicito el cliente o primera vez que obtuve el token de sesion
            if (mInstance == null) {
                mInstance = MiniTwitterClient(provideClient())
            }
            return mInstance as MiniTwitterClient
        }

        private fun provideClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor { chain ->
                    var request = chain.request()
                    val builder = request.newBuilder()
                    builder.addHeader("Accept", "application/json")
                    val token = MiniTwitter.getAuthResponse().token
                    token?.let {
                        builder.addHeader("Authorization", "Bearer $it").build()
                    }
                    request = builder.build()
                    chain.proceed(request)

                }
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)


            //TODO: Trust certificates temporarily. Delete on production
            try {
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<java.security.cert.X509Certificate>,
                        authType: String
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<java.security.cert.X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return emptyArray()
                    }
                })

                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                val sslSocketFactory = sslContext.socketFactory

                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })

            } catch (e: Exception) {
                e.printStackTrace()
            }

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
            return builder.build()
        }
    }
}