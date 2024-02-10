package com.suryodayach.nychighschools.data

import com.suryodayach.nychighschools.data.model.HighSchool
import com.suryodayach.nychighschools.data.remote.NycApiService
import com.suryodayach.nychighschools.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NYCRepository @Inject constructor(
    private val restInterface: NycApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun getHighSchools(): Flow<List<HighSchool>> = flow {
        val response = withContext(dispatcher) {
            restInterface.getHighSchools()
        }
        emit(response)
    }.flowOn(dispatcher)

    suspend fun getHighSchool(dbn: String): Flow<HighSchool> = flow<HighSchool> {
        val response = withContext(dispatcher) {
            restInterface.getHighSchool(dbn)
                .first()
        }
        emit(response)
    }.flowOn(dispatcher)

}