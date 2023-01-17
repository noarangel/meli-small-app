# SmallMeliApp
This App is a challenge for the position at Mercado Libre, it is a simple app that shows a list of
products and their details, it also allows you to search for products

## Arquitecture
This proyect is based on the MVVM arquitecture, it uses the following libraries for core implementation:

- [Retrofit2](https://square.github.io/retrofit/) for the network calls
- [OkHttp3](https://square.github.io/okhttp/) for the network calls
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- [Coroutines](https://developer.android.com/kotlin/coroutines) for asynchronous tasks

and test implementation

- [JUnit](https://junit.org/junit5/) for unit testing
- [mockk](https://mockk.io/) for mocking
- [HiltTest](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection in tests
- [okhttpTest](https://square.github.io/okhttp/) for test network calls
- [CoroutinesTest](https://developer.android.com/kotlin/coroutines) for test asynchronous tasks
- [ArchCoreTest](https://developer.android.com/jetpack/androidx/releases/arch-core) for test LiveData

Proyect package structure:

```
app -
    |- src -
            |- main -
            |- java -
                |- com -
                    |- meli -
                        |- app -
                            |- core
                                |- api -
                                |- di -
                                |- extension -
                                |- service -
                                |- view -
                            |- model -
                            |- home -
                                |- api
                                |- repository
                                |- ui
                                |- viewmodel
                                |- usecase
                            |- product -
                                |- api
                                |- repository
                                |- ui
                                |- viewmodel
                                |- usecase
                            |- product
                                |- api
                                |- repository
                                |- ui
                                |- viewmodel
                                |- usecase
                            |- utils -
            |- res -
                |- drawable
                |- layout
                |- navigation
                |- raw
                |- values
        |- test -
            |- java -
                |- com -
                    |- meli -
                        |- app -
                            |- model -
                            |- repository -
```
## How to run

| Configurations   |      Value      |
|------------------|:---------------:|
| minSdk           |       21        |
| targetSdk        |       33        |
| compileSdk       |       33        |

```
- Clone the repository
https://github.com/noarangel/meli-small-app.git
- Open the project with Android Studio
- Run the project
```
## Configuration
### Proyect flavors

```groovy
`#0d1117`
    productFlavors {
        develop {
            dimension "api"
            applicationIdSuffix ".develop"
            buildConfigField 'String', 'BASE_URL', '"https://api.mercadolibre.com/"'
            buildConfigField 'String', 'SHARED_PREFERENCES_DB_0', '"DB0"'
        }
    }
```
### Gradle dependencies:
```groovy
    dependencies {
        implementation 'androidx.core:core-ktx:1.9.0'
        implementation 'androidx.appcompat:appcompat:1.6.0'
        implementation 'com.google.android.material:material:1.7.0'
        implementation 'androidx.recyclerview:recyclerview:1.2.1'
        implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
        implementation 'androidx.navigation:navigation-fragment-ktx:2.5.3'
        implementation 'androidx.navigation:navigation-ui-ktx:2.5.3'
        implementation 'androidx.databinding:databinding-runtime:7.3.1'

        //core
        implementation 'com.squareup.retrofit2:retrofit:2.9.0'
        implementation "com.squareup.retrofit2:converter-gson:2.9.0"
        kapt 'com.google.dagger:hilt-compiler:2.44.2'
        implementation 'com.google.dagger:hilt-android:2.44.2'
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
        implementation 'com.squareup.okhttp3:okhttp-bom:4.10.0'
        implementation 'com.squareup.okhttp3:okhttp'
        implementation 'com.squareup.okhttp3:logging-interceptor'
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'


        //resources
        implementation 'com.squareup.picasso:picasso:2.8'
        implementation 'com.airbnb.android:lottie:3.3.1'

        //test
        testImplementation 'junit:junit:4.13.2'
        testImplementation "io.mockk:mockk:1.13.3"
        androidTestImplementation 'androidx.test.ext:junit:1.1.5'
        kaptTest 'com.google.dagger:hilt-compiler:2.44.2'
        androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
        testImplementation 'com.google.dagger:hilt-android-testing:2.44.2'
        testImplementation 'com.squareup.okhttp3:mockwebserver:4.10.0"'
        testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
        testImplementation "androidx.arch.core:core-testing:2.1.0"
    }
```
### Configure base url api:
````gradle
    buildTypes {
        debug {
            buildConfigField 'String', 'BASE_URL', '"https://api.mercadolibre.com/"'
        }
        release {
            buildConfigField 'String', 'BASE_URL', '"https://api.mercadolibre.com/"'
        }
    }
```
````gradle
    object RetrofitModule {
       ...
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
           ...
       }
       ...
````
### Standart of create a funcionality
#### Create a new package funcionality
```
    |- src -
            |- main -
            |- java -
                |- com -
                    |- meli -
                        |- app -
                            |- core
                                |- api -
                                |- di -
                                |- extension -
                                |- service -
                                |- view -
                            |- model -
                            |- home -
                                |- api
                                |- repository
                                |- ui
                                |- viewmodel
                                |- usecase
                            |- product -
                                |- api
                                |- repository
                                |- ui
                                |- viewmodel
                                |- usecase
                            |- product
                                |- api
                                |- repository
                                |- ui
                                |- viewmodel
                                |- usecase
                            |- utils -
```
1. Create a new package of functionality
```
   ... |- app -
          |- src -
             |- java -
```
2. add necessary package
```
  |- nameoffuncionality -
     |- api
     |- repository
     |- usecase
     |- viewmodel
     |- ui
```
3. Create a new class in package api
```
  |- nameoffuncionality -
     |- api
        |- NameOfFuncionalityDataSource.kt
```
4. Create a new class in package repository
```
  |- nameoffuncionality -
     |- repository
        |- NameOfFuncionalityRepository.kt
```
5. Create a new class in package usecase
```
  |- nameoffuncionality -
     |- usecase
        |- NameOfFuncionalityUseCase.kt
```
6. Create a new class in package viewmodel
```
  |- nameoffuncionality -
     |- viewmodel
        |- NameOfFuncionalityViewModel.kt
```
7. Create a new class in package ui
```
  |- nameoffuncionality -
     |- ui
        |- NameOfFuncionalityFragment.kt
```
#### implement models
1. Create a new class in package model
```
  |- model
     |- NameOfFuncionalityModel.kt
```
2. Create a class implementation
```
package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class NameOfFuncionalityModel(
    @SerializedName("name_in_json") val nameInJson: String,
    @SerializedName("name_response") val nameResponse: String,
)

#### Create a new class in package api

````kotlin
    interface NameOfFuncionalityDataSource {
    ...
        @GET("api/path")
        suspend fun getServiceName(
            @Path("value_in_path") valueInPath: String,
            @Query("value_to_replace") valueToReplace: String
        ): ApiResponse<ResponseClassModel>
     ...
    }
````
#### Create a new class in package repository

````kotlin
    suspend fun getServiceName(valueInPath: String, valueToReplace: String) =
            withContext(Dispatchers.IO) {
                when (val response = api.getProductsBySearch(siteId, findText)) {
                    is ApiSuccessResponse -> ApiResult.Success(response.data)
                    is ApiEmptyResponse -> ApiResult.Error(
                        context.getString(R.string.default_empty_error).toApiException()
                    )
                    is ApiErrorResponse -> ApiResult.Error(
                        context.getString(R.string.default_error).toApiException()

                    )
                }
            }
````
#### Create a new class in package usecase

````kotlin
     @Inject
        lateinit var context: Context

        suspend operator fun invoke(valueInPath: String, valueToReplace: String): ApiResult<SearchResultsModel> {
            homeSharedPreferencesRepository.getUserSite().let { userSite ->
                return when (val response = homeRepository.getServiceName(valueInPath, valueToReplace)) {
                    is ApiResult.Success -> ApiResult.Success(response.data)
                    is ApiResult.Error -> ApiResult.Error(response.exception)
                }
            }
        }
````
#### Create a new class in package viewmodel

````kotlin
    @HiltViewModel
    class NameOfFuncionalityViewModel @Inject constructor(
        private val nameOfFuncionalityUseCase: NameOfFuncionalityUseCase,
    ) : ViewModel() {

        val mutableValue = MutableLiveData<TypeValue>()
        val isLoading = MutableLiveData<Boolean>(false)

        fun init() {
            viewModelScope.launch {
                isLoading.postValue(true)
                when (val response = nameOfFuncionalityUseCase()) {
                    is ApiResult.Error -> errorObserver.postValue(response.exception)
                    is ApiResult.Success -> mutableValue.postValue(
                        response.data
                    )
                }
                isLoading.postValue(false)
            }
        }
       <!-- implement methods of viewmodel -->
````



