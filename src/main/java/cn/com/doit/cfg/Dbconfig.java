//package cn.com.doit.cfg;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
///*���Դ����*/
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import cn.com.doit.util.MapperOfMybatis;
//
//
//@Configuration
//@EnableTransactionManagement
//@MapperScan(basePackages="cn.com.doit.pojo.login.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
//public class Dbconfig {
//	 
//	@Bean(name="dataSource")
//    @ConfigurationProperties(prefix = "demo.jdbc")
//    public DataSource demoDataSource() {
//		System.out.println("========********************************************************************************========");
//        return DataSourceBuilder.create()
//        		.type(com.zaxxer.hikari.HikariDataSource.class).build();
//    }
//
//    @Bean(name ="sqlSessionFactory")
//    public SqlSessionFactory portalSqlSessionFactory(@Qualifier("dataSource") DataSource demoDataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(demoDataSource);
//        demoDataSource.getConnection();
//        bean.setConfigLocation(new ClassPathResource("MybatisConfig.xml"));
//        return bean.getObject();
//    }
//	@Bean(name="dotiJdbcTemplate")
//    public JdbcTemplate trainJdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//	
//	@Bean(name="trainTransactionManager")
//    public PlatformTransactionManager trainTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//    }
//
//	
//}
