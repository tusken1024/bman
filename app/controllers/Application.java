package controllers;

import java.util.List;

import models.Contact;
import models.Task;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Form<Task> taskForm = form(Task.class);

	public static Form<Contact> contactForm = form(Contact.class);

	public static Result index() {
		return redirect(routes.Application.tasks());
	}

	public static Result tasks() {
		return ok(views.html.index.render(Task.all(), taskForm));
	}

	public static Result jsonTasks() {
		List<Task> tasks = new Model.Finder(String.class, Task.class).all();
		return ok(Json.toJson(tasks));
	}

	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(Task.all(), filledForm));
		} else {
			Task.create(filledForm.get());
			return redirect(routes.Application.tasks());
		}
	}

	public static Result deleteTask(Long id) {
		Task.delete(id);
		return redirect(routes.Application.tasks());
	}

	public static Result contacts() {
		return ok(views.html.contact.render(Contact.all(), contactForm));
	}

	public static Result newContact() {
		Form<Contact> filledForm = contactForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.contact.render(Contact.all(),
					filledForm));
		} else {
			Contact.create(filledForm.get());
			return redirect(routes.Application.contacts());
		}
	}

	public static Result deleteContact(Long id) {
		Contact.delete(id);
		return redirect(routes.Application.contacts());
	}
}