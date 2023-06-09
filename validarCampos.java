import java.util.Random;

/**
 * Clase que comprueba si los campos introducidos por el usuario son válidos y
 * genera un código de seguridad aleatorio de 8 caracteres para su posterior
 * comprobación.
 */
public class validarCampos {

    String[] names = { "Ricardo", "Ignasi", "Miquel", "Jaume", "Pere", "Joan", "Pedro", "Juan", "Antonio", "Albacete" };
    String[] domains = { "paucasesnovescifp", "yahoo", "gmail", "hotmail" };
    String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String numeros = "0123456789";
    String simbolos = "!@#$%^&*()_+-={}[]\\|:;\"'<>,.?/";

    /**
     * Método que comprueba si los campos introducidos son válidos y el código de
     * seguridad generado coincide con el introducido por el usuario.
     * 
     * @param name     Nombre del usuario.
     * @param email    Dirección de correo electrónico del usuario.
     * @param password Contraseña elegida por el usuario.
     * @param code     Código de seguridad introducido por el usuario.
     * @return True si todos los campos son válidos y los códigos coinciden, False
     *         en caso contrario.
     */
    public boolean check(String name, String email, String password, String code) {

        // Generación del código de seguridad aleatorio
        String caracteres = letras + numeros + simbolos;
        Random rand = new Random();
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            codigo.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }
        String codigo2 = codigo.toString();
        System.out.println(codigo2);

        // Comprobación de que el nombre no está repetido
        for (int i = 0; i < names.length; i++) {
            if (name == names[i]) {
                System.out.println("name already exists");
                return false;
            }
        }

        // Comprobación de que el nombre tiene una longitud válida
        if (name.length() < 3 || name.length() > 16) {
            System.out.println("name must be between 3 and 16 characters");
            return false;
        }

        // Comprobación de que el email es válido
        boolean containsDomain = false;
        boolean endsWith = false;
        for (String domain : domains) {
            if (email.contains(domain)) {
                containsDomain = true;
                break;
            }
        }
        if (email.endsWith(".com") || email.endsWith(".cat")) {
            endsWith = true;
        }
        if (containsDomain == false || endsWith == false) {
            System.out.println("email must be correct");
            return false;
        }

        // Comprueba que la contraseña tiene 8 caracteres
        if (password.length() != 8) {
            System.out.println("Password must be 8 characters long");
            return false;
        }

        // Comprueba que la contraseña empieza por una letra mayúscula
        char firstChar = password.charAt(0);
        if (!Character.isUpperCase(firstChar)) {
            System.out.println("Password must start with a capital letter");
            return false;
        }

        // Comprueba que la contraseña contiene un carácter especial
        boolean containsSpecialChar = false;
        String specialChars = "@-#_";
        for (int i = 0; i < specialChars.length(); i++) {
            char c = specialChars.charAt(i);
            if (password.indexOf(c) != -1) {
                containsSpecialChar = true;
                break;
            }
        }
        if (!containsSpecialChar) {
            System.out.println("Password must contain a special character");
            return false;
        }

        // Comprueba que la contraseña acaba en dos números
        char lastChar = password.charAt(password.length() - 1);
        char secondLastChar = password.charAt(password.length() - 2);
        if (!Character.isDigit(lastChar) || !Character.isDigit(secondLastChar)) {
            System.out.println("Password must end with 2 integers");
            return false;
        }

        System.out.println(codigo2);
        if (codigo2 != code) {
            System.out.println("The codes must be equal");
            return false;
        }
        return true;
    }
}
