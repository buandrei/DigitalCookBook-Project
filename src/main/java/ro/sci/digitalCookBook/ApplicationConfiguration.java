package ro.sci.digitalCookBook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.sci.digitalCookBook.dao.RecipeDAO;
import ro.sci.digitalCookBook.dao.db.JDBCRecipeDAO;
import ro.sci.digitalCookBook.service.RecipeService;

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
