package com.ramonapp.android.bestpracticetask.data

import com.google.common.truth.Truth.assertThat
import com.ramonapp.android.bestpracticetask.data.datasource.remote.mapper.toAlbumModel
import com.ramonapp.android.bestpracticetask.data.datasource.remote.mapper.toPhotoModel
import com.ramonapp.android.bestpracticetask.network.model.AlbumDto
import com.ramonapp.android.bestpracticetask.network.model.PhotoDto
import org.junit.Test

class DtoMapperTest {

    @Test
    fun `test album dto list mapper to album model list`() {
        val dtoList = listOf(
            AlbumDto(0L, 0L, "a"),
            AlbumDto(null, 1L, "b"),
            AlbumDto(2L, null, "c"),
            AlbumDto(3L, 3L, null),
        )

        val modelList = dtoList.toAlbumModel()

        assertThat(modelList).hasSize(2)
    }


    @Test
    fun `test photo dto list mapper to photo model list`() {
        val dtoList = listOf(
            PhotoDto(0L, null, "a", "i1"),
            PhotoDto(1L, 0L, "a", null),
            PhotoDto(null, 0L, "a", ""),
            PhotoDto(3L, 3L, "", "i4"),
            PhotoDto(4L, 4L, null, "i5"),
        )

        val modelList = dtoList.toPhotoModel()

        assertThat(modelList).hasSize(3)
    }

}