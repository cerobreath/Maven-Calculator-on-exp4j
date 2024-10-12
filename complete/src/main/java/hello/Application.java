package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@SpringBootApplication
@RestController
public class Application {

	// Метод для відображення головної сторінки з формою введення
	@RequestMapping("/")
	public String home() {
		return """
    <html>
    <head>
        <link rel="stylesheet" type="text/css" href="/style.css"/>
    </head>
    <body>
        <h1>Calculator</h1>
        <form action="/calculate" method="get">
            <input type="text" name="expression" placeholder="Enter expression"/>
            <button type="submit">Calculate</button>
        </form>
    </body>
    </html>
    """;
	}

	// Метод для обчислення виразу, отриманого від користувача
	@RequestMapping("/calculate")
	public String calculate(@RequestParam String expression) {
		try {
			// Використовуємо exp4j для обчислення математичного виразу
			Expression e = new ExpressionBuilder(expression).build();
			double result = e.evaluate();
			// Повертаємо результат обчислення
			return "<html>" +
					"<head><link rel='stylesheet' type='text/css' href='/style.css'/></head>" +
					"<body>" +
					"<h1>Result: " + result + "</h1>" +
					"<a href='/'>Go back</a>" +
					"</body></html>";
		} catch (Exception e) {
			// Якщо виникає помилка (наприклад, некоректний вираз)
			return "<html>" +
					"<head><link rel='stylesheet' type='text/css' href='/style.css'/></head>" +
					"<body>" +
					"<h1>Error: invalid expression</h1>" +
					"<a href='/'>Go back</a>" +
					"</body></html>";
		}
	}


	// Головний метод для запуску програми
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
