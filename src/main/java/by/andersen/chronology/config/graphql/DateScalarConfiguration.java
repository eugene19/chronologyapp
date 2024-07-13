package by.andersen.chronology.config.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import graphql.scalars.ExtendedScalars;

@Configuration
public class DateScalarConfiguration  {

    /**
     * Bean for configuring the runtime wiring of the GraphQL schema.
     * This bean configures the scalar type for dates.
     *
     * @return the runtime wiring configurer
     */
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date);
    }

}
