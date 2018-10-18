package com.example.zumer.kamino.model

import java.io.Serializable

data class Resident (
    var name: String?,
    var height: String?,
    var mass: String?,
    var hair_color: String?,
    var skin_color: String?,
    var eye_color: String?,
    var birth_year: String?,
    var gender: String?,
    var homeworld: String?,
    var image_url: String?
): Serializable