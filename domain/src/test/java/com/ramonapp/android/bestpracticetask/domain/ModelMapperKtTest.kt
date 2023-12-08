package com.ramonapp.android.bestpracticetask.domain

import com.google.common.truth.Truth.assertThat
import com.ramonapp.android.bestpracticetask.domain.model.AlbumModel
import com.ramonapp.android.bestpracticetask.domain.model.PhotoModel
import com.ramonapp.android.bestpracticetask.domain.model.UserModel
import com.ramonapp.android.bestpracticetask.domain.model.mapToAlbumCombination
import org.junit.Test


class ModelMapperKtTest {

    @Test
    fun `test mapToAlbumCombination`() {
        val album = listOf(
            AlbumModel(1,1,"flowers"),
            AlbumModel(2,3,"books"),
        )
        val photo = listOf(
            PhotoModel(3,123,"book1","")
        )
        val user = listOf(
            UserModel(1,"Alex"),
            UserModel(2,"Sam")
        )

        assertThat(mapToAlbumCombination(album, null, user)).hasSize(2)
        assertThat(mapToAlbumCombination(album, photo, user)).hasSize(2)
        assertThat(mapToAlbumCombination(album, photo, user)?.first()?.albumTitle).isEqualTo("flowers")
        assertThat(mapToAlbumCombination(album, photo, user)?.last()?.albumTitle).isEqualTo("books")
        assertThat(mapToAlbumCombination(album, photo, null)?.first()?.username).isEqualTo("")
        assertThat(mapToAlbumCombination(album, photo, user)?.first()?.username).isEqualTo("Alex")
    }
}