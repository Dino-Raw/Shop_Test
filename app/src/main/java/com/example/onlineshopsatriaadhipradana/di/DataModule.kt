package com.example.onlineshopsatriaadhipradana.di

import dagger.Module

@Module(includes = [DataBindModule::class, RoomModule::class, RetrofitModule::class])
class DataModule {
}