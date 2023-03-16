package com.example.data.entity

import com.example.domain.model.*

fun UserSignInDomain.toData() = UserData (
    firstName = this.firstName!!,
    lastName = this.lastName!!,
    email = this.email!!,
)

fun FlashSaleListData.toDomainList() = arrayListOf<FlashSaleDomain>().apply {
    this@apply.addAll(
        this@toDomainList.flashSale.map {
            FlashSaleDomain (
                category = it.category,
                name = it.name,
                price = it.price,
                discount = it.discount,
                imageUrl = it.imageUrl,
            )
        }
    )
}

fun LatestListData.toDomainList() = arrayListOf<LatestDomain>().apply {
    this@apply.addAll(
        this@toDomainList.latest.map {
            LatestDomain(
                category = it.category,
                name = it.name,
                price = it.price,
                imageUrl = it.imageUrl,
            )
        }
    )
}

fun ProductData.toDomain() = ProductDomain (
    name = this.name,
    description = this.description,
    rating = this.rating,
    numberOfReviews = this.numberOfReviews,
    price = this.price,
    colors = this.colors,
    imageUrls = this.imageUrls,
)