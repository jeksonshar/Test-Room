package com.example.testlibrarysong.business.usecases

import com.example.testlibrarysong.business.domain.models.PlayList
import com.example.testlibrarysong.datasourse.room.MusicDataBase

class GetSimilarPlaylistsByTwoUsersUseCase {

    fun getSimilarPlaylistsByTwoUsers(firstList: List<PlayList>, secondList: List<PlayList>): List<PlayList> {
        val list = arrayListOf<PlayList>()
        for (element in firstList) {
            if (secondList.contains(element)) {
                list.add(element)
            }
        }
        return list
    }

}