import com.suryodayach.nychighschools.data.NYCRepository
import com.suryodayach.nychighschools.data.model.HighSchool
import com.suryodayach.nychighschools.data.remote.NycApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class NYCRepositoryTest {

    @Mock
    private lateinit var mockApiService: NycApiService

    private lateinit var repository: NYCRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = NYCRepository(mockApiService, testDispatcher)
    }

    @Test
    fun `getHighSchools returns expected data`() = runTest {
        val fakeHighSchools = listOf(
            HighSchool(dbn = "111", schoolName = "School 1"),
            HighSchool(dbn = "222", schoolName = "School 2")
        )
        `when`(mockApiService.getHighSchools()).thenReturn(fakeHighSchools)

        val result = repository.getHighSchools().first()

        assertEquals(fakeHighSchools, result)
    }

    @Test
    fun `getHighSchool returns expected data`() = runTest {
        val fakeHighSchool = HighSchool(dbn = "111", schoolName = "School 1")
        `when`(mockApiService.getHighSchool("111")).thenReturn(listOf(fakeHighSchool))

        val result = repository.getHighSchool("111").first()

        assertEquals(fakeHighSchool, result)
    }
}
