package com.meghalife.app.data

import com.meghalife.app.R

val touristPOIs = listOf(

    TouristPOI(
        name = "Elephant Falls",
        lat = 25.5362,
        lng = 91.8540,
        category = "Waterfall",
        description = "One of the most popular waterfalls near Shillong.",
        images = listOf(
            R.drawable.poi_elephant_falls_1,
            R.drawable.poi_elephant_falls_2
        ),
        transportInfo =
            "Shared taxis available from Police Bazaar, Shillong.",
        transportContacts = listOf(
            "+91 9XXXX XXXXX",
            "+91 8XXXX XXXXX"
        ),
        bestMonths = listOf(3, 4, 5, 9, 10)
    ),

    TouristPOI(
        name = "Umiam Lake",
        lat = 25.6806,
        lng = 91.9140,
        category = "Nature",
        description = "A scenic lake surrounded by hills, ideal for photography.",
        images = listOf(
            R.drawable.poi_umiam_1,
            R.drawable.poi_umiam_2
        ),
        transportInfo =
            "Direct buses and shared taxis from ISBT Mawiong.",
        transportContacts = listOf(
            "+91 7XXXX XXXXX"
        ),
        bestMonths = listOf(2, 3, 4, 10, 11)
    )
)
