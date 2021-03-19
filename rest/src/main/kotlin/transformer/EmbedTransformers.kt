package dev.kord.rest.transformer

import dev.kord.common.Color
import dev.kord.common.entity.DiscordEmbed
import dev.kord.common.entity.optional.map
import dev.kord.common.entity.optional.mapList
import dev.kord.rest.json.request.*

fun DiscordEmbed.toRequest(): EmbedRequest =
    EmbedRequest(
        title,
        type.map { it.value },
        description,
        url,
        timestamp,
        color.asOptional.map { Color(it) },
        footer.map { EmbedFooterRequest(it.text, it.iconUrl) },
        image.map { EmbedImageRequest(it.url.value!!) },
        thumbnail.map { EmbedThumbnailRequest(it.url.value!!) },
        author.map { EmbedAuthorRequest(it.name, it.url, it.iconUrl) },
        fields.mapList { EmbedFieldRequest(it.name, it.value, it.inline) }
    )