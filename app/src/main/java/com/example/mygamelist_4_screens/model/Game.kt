package com.example.mygamelist_4_screens.model

import com.google.gson.annotations.SerializedName

data class Game(
    val success: Boolean,
    val data: GameData
)

data class GameData(
    val type: String,
    val name: String,
    val steam_appid: Int,
    val required_age: String,
    val is_free: Boolean,
    val controller_support: String,
    val dlc: List<Int>,
    val detailed_description: String,
    val about_the_game: String,
    val short_description: String,
    val supported_languages: String,
    val header_image: String,
    val capsule_image: String,
    val capsule_imagev5: String,
    val website: String,
    val pc_requirements: Requirements,
    val mac_requirements: Requirements,
    val linux_requirements: Requirements,
    val legal_notice: String,
    val developers: List<String>,
    val publishers: List<String>,
    val price_overview: PriceOverview,
    val package_groups: List<PackageGroup>,
    val platforms: Platforms,
    val metacritic: Metacritic,
    val categories: List<Category>,
    val genres: List<Genre>,
    val screenshots: List<Screenshot>,
    val movies: List<Movie>,
    val recommendations: Recommendations,
    val achievements: Achievements,
    val release_date: ReleaseDate,
    val support_info: SupportInfo,
    val background: String,
    val background_raw: String,
    val content_descriptors: ContentDescriptors
)

data class Requirements(
    val minimum: String,
    val recommended: String
)

data class PriceOverview(
    val currency: String,
    val initial: Int,
    val final: Int,
    val discount_percent: Int,
    val initial_formatted: String,
    val final_formatted: String
)

data class PackageGroup(
    val name: String,
    val title: String,
    val description: String,
    val selection_text: String,
    val save_text: String,
    val display_type: Int,
    val is_recurring_subscription: String,
    val subs: List<Package>
)

data class Package(
    val packageid: Int,
    val percent_savings_text: String,
    val percent_savings: Int,
    val option_text: String,
    val option_description: String,
    val can_get_free_license: String,
    val is_free_license: Boolean,
    val price_in_cents_with_discount: Int
)

data class Platforms(
    val windows: Boolean,
    val mac: Boolean,
    val linux: Boolean
)

data class Metacritic(
    val score: Int,
    val url: String
)

data class Category(
    val id: Int,
    val description: String
)

data class Genre(
    val id: String,
    val description: String
)

data class Screenshot(
    val id: Int,
    val path_thumbnail: String,
    val path_full: String
)

data class Movie(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val webm: Map<String, String>,
    val mp4: Map<String, String>,
    val highlight: Boolean
)

data class Recommendations(
    val total: Int
)

data class Achievements(
    val total: Int,
    val highlighted: List<Achievement>
)

data class Achievement(
    val name: String,
    val path: String
)

data class ReleaseDate(
    val coming_soon: Boolean,
    val date: String
)

data class SupportInfo(
    val url: String,
    val email: String
)

data class ContentDescriptors(
    val ids: List<Int>,
    val notes: String
)

data class GameDB(
    val name: String,
    val description: String,
    val image: String
)
