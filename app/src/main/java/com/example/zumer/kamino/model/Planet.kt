package com.example.zumer.kamino.model

data class Planet (
    var name: String?,
    var rotation_period: String?,
    var orbital_period: String?,
    var diameter: String?,
    var climate: String?,
    var gravity: String?,
    var terrain: String?,
    var surface_water: String?,
    var population: String?,
    var residents: ArrayList<String>?,
    var image_url: String?,
    var likes: Int?
)