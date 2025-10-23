package diomaxius.whattocookwith.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import diomaxius.whattocookwith.data.dao.IngredientDao
import diomaxius.whattocookwith.data.dao.RecipeDao
import diomaxius.whattocookwith.data.database.IngredientDatabase
import diomaxius.whattocookwith.data.repository.IngredientRepositoryImpl
import diomaxius.whattocookwith.data.repository.RecipeRepositoryImpl
import diomaxius.whattocookwith.domain.repository.IngredientRepository
import diomaxius.whattocookwith.domain.repository.RecipeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        IngredientDatabase::class.java,
        IngredientDatabase.DATABASE_NAME
    ).createFromAsset("ingredients_db").build()

    @Provides
    @Singleton
    fun provideIngredientDao(
        db: IngredientDatabase,
    ) = db.ingredientDao()

    @Provides
    @Singleton
    fun provideIngredientRepository(
        dao: IngredientDao,
    ): IngredientRepository = IngredientRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideRecipeDao(
        db: IngredientDatabase,
    ) = db.recipeDao()

    @Provides
    @Singleton
    fun provideRecipeRepository(
        dao: RecipeDao,
    ): RecipeRepository = RecipeRepositoryImpl(dao)
}