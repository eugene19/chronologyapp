package by.andersen.chronology.config.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Annotation to indicate that the annotated method requires ADMIN role.
 * Can be used at the method level of a controller to indicate that only
 * users with ADMIN role are allowed to access such methods.
 *
 * Example:
 * <pre>
 * 	&#64;HasAdminRole
 * 	&#64;GetMapping("/admin")
 * 	public String admin() {
 * 		return "admin";
 * 	}
 * </pre>
 *
 * @author Yauhen Dzehtsiarou
 * @since 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasRole(T(by.andersen.chronology.config.security.UserRole).ADMIN.name())")
public @interface HasAdminRole {

}
