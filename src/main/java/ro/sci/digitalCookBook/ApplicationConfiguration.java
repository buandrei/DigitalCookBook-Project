package ro.sci.digitalCookBook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ro.sci.digitalCookBook.dao.*;
import ro.sci.digitalCookBook.dao.db.*;
import ro.sci.digitalCookBook.service.*;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.name}")
    private String dbName;

    @Bean
    public RecipeDAO recipeDAO() {
        return new JDBCRecipeDAO(dbHost, "5432", dbName, dbUser, dbPassword);
    }

    @Bean
    public RecipeService recipeService() {
        RecipeService recipeService = new RecipeService();

        recipeService.setDao(recipeDAO());
        return recipeService;
    }

    @Bean
    public RecipePhotoDAO recipePhotoDAO() {
        return new JDBCRecipePhotoDAO(dbHost, "5432", dbName, dbUser, dbPassword);
    }

    @Bean
    public RecipePhotoService recipePhotoService() {
        RecipePhotoService recipePhotoService = new RecipePhotoService();

        recipePhotoService.setDao(recipePhotoDAO());
        return recipePhotoService;
    }

    @Bean
    public RecipeCategoryDAO recipeCategory() {
        return new JDBCRecipeCategoryDAO(dbHost, "5432", dbName, dbUser, dbPassword);
    }

    @Bean
    public RecipeCategoryService recipeCategoryService() {
        RecipeCategoryService recipeCategoryService = new RecipeCategoryService();

        recipeCategoryService.setDao(recipeCategory());
        return recipeCategoryService;
    }

    @Bean
    public RecipeIngredientDAO recipeIngredients() {
        return new JDBCRecipeIngredientDAO(dbHost, "5432", dbName, dbUser, dbPassword);
    }

    @Bean
    public RecipeIngredientsService recipeIngredientsService() {
        RecipeIngredientsService recipeIngredientsService = new RecipeIngredientsService();

        recipeIngredientsService.setDao(recipeIngredients());
        return recipeIngredientsService;
    }

    @Bean
    public IngredientDAO ingredientDAO() {
        return new JDBCIngredientDAO(dbHost, "5432", dbName, dbUser, dbPassword);
    }

    @Bean
    public IngredientService IngredientsService() {
        IngredientService ingredientService = new IngredientService();

        ingredientService.setDao(ingredientDAO());
        return ingredientService;
    }

    @Bean
    public ViewResolver beanNameViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        return resolver;
    }

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        String path = System.getProperty("user.dir") + "/src/main/resources/retete/";

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    @Bean
    public DataSource dataSource() {
        String url = new StringBuilder()
                .append("jdbc:")
                .append("postgresql")
                .append("://")
                .append(dbHost)
                .append(":")
                .append("5432")
                .append("/")
                .append(dbName)
                .append("?user=")
                .append(dbUser)
                .append("&password=")
                .append(dbPassword).toString();

        return new SingleConnectionDataSource(url, false);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
