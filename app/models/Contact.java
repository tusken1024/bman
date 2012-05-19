/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * @author mejdi
 * 
 */
@Entity
public class Contact extends Model {

	public static Finder<Long, Contact> find = new Finder(Long.class,
			Contact.class);

	@Id
	public Long id;

	@Required
	public String nom;

	public String prenom;

	public String pseudo;

	@Required
	public String telephone;

	public String observation;

	public static List<Contact> all() {
		return find.all();
	}

	public static void create(Contact contact) {
		contact.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

}