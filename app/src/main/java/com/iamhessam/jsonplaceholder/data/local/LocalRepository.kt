package com.iamhessam.jsonplaceholder.data.local

import androidx.room.RoomDatabase

class LocalRepository(room: RoomDatabase) {

    var appDB: RoomDatabase = room
        private set
}