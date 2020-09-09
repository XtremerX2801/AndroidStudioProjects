package com.example.myapplicationss.Model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Doc(

    @SerializedName("abstract")
    @Expose
    val abstract_object: String?,

    val byline: Byline?,

    @SerializedName("document_type")
    @Expose
    val documentType: String?,

    val headline: Headline?,

    @SerializedName("_id")
    @Expose
    val id: String?,

    val keywords: List<Keyword>?,

    @SerializedName("lead_paragraph")
    @Expose
    val leadParagraph: String?,

    val multimedia: List<Multimedia>?,

    @SerializedName("news_desk")
    @Expose
    val newsDesk: String?,

    @SerializedName("print_page")
    @Expose
    val printPage: String?,

    @SerializedName("print_section")
    @Expose
    val printSection: String?,

    @SerializedName("pub_date")
    @Expose
    val pubDate: String?,

    @SerializedName("section_name")
    @Expose
    val sectionName: String?,

    val snippet: String?,

    val source: String?,

    @SerializedName("subsection_name")
    @Expose
    val subsectionName: String?,

    @SerializedName("type_of_material")
    @Expose
    val typeOfMaterial: String?,

    val uri: String?,

    @SerializedName("web_url")
    @Expose
    val webUrl: String?,

    @SerializedName("word_count")
    @Expose
    val wordCount: Int?
): Parcelable