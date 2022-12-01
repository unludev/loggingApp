package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * LoggerLocalDataSource, scope'u application containerina göre ayarlandığından,(@Singleton olarak yani)
 * LogDao bindinginin application containerinda bulunması gerekir. @InstallIn annotation kullanarak,
 * onunla ilişkili Hilt componentinin sınıfını (yani, SingletonComponent:class) geçirerek bu gereksinimi belirtiriz:
 */
@Module //bu class in module oldugunu soyler, farkli tipteki yapilara dependency eklemek icin module gereklidir(room, retrofit, interface)
@InstallIn(SingletonComponent::class) //bir Hilt componenti burada (SingletonComponent) belirterek Hilt'e bindinglerin bulunduğu containerlari söyler.
object DatabaseModule {
    @Provides //Hilt'e constructor edilemeyen türleri nasıl sağlayacağını söylemek için Hilt modüllerinde @Provides ile bir fonksiyona annotation ekleyebiliriz.
    fun provideLogDao(database: AppDatabase): LogDao {  //function parametresi gerekli olan instance'in bagimliliklarini belirtir
        //function bodysi gerekli olan instance tipini dondurur
        return database.logDao()
    }

    //yukarida logdao cagrilirken nasil saglanacagini soyledik. Databasemizinde nasil saglanacagini belirtmemiz gerkir.
    //yine Appdatabase harici bir kutuphanede olan bir sinif old. icin constructor inject yapamayaiz, provides ile saglayacagiz.
    @Provides
    @Singleton //singleton olmasini istedigimiz icin
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        //hilt onceden tanimli bazi annotationlar ile beraber geelir buradaki
        // @ApplicationContext gibi bu annotation bize context saglar
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }
}

