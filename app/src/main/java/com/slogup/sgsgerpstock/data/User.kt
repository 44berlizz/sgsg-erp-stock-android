package com.slogup.sgsgerpstock.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes

/**
 * Created by jessehj on 25/03/2019.
 */

@Entity(tableName = "user_table")
data class User(
    @SerializedName("aId") @PrimaryKey @NonNull
    val aId: String = "",
    @SerializedName("groupKey")
    val groupKey: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("pass")
    val pass: String? = "",
    @SerializedName("salt")
    val salt: String? = "",
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("name")
    val name: Name,
    @SerializedName("nick")
    val nick: String? = "",
    @SerializedName("role")
    val role: String? = "",
    @SerializedName("gender")
    val gender: String? = "",
    @SerializedName("birth")
    val birth: String? = "",
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("di")
    val di: String? = "",
    @SerializedName("ci")
    val ci: String? = "",
    @SerializedName("auth")
    val auth: Boolean?,
    @SerializedName("passUpdatedAt")
    val passUpdatedAt: String? = "",
    @SerializedName("leftAt")
    val leftAt: String? = "",
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("updatedAt")
    val updatedAt: String? = ""
) {
    data class Name(
        @SerializedName("lastName")
        val lastName: String,
        @SerializedName("firstName")
        val firstName: String
    )
}

