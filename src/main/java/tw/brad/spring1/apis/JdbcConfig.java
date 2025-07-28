package tw.brad.spring1.apis;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class JdbcConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.brad")
	public DataSource bradDataSource() {
		System.out.println("1");
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public NamedParameterJdbcTemplate bradJdbc(@Qualifier("bradDataSource") DataSource dataSource) {
		System.out.println("2");
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.north")
	public DataSource northDataSource() {
		System.out.println("1");
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public NamedParameterJdbcTemplate northJdbc(@Qualifier("northDataSource") DataSource dataSource) {
		System.out.println("2");
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
