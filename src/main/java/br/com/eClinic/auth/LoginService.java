package br.com.eClinic.auth;

public class LoginService {

    public void validarLogin(String email, String senha) throws Exception {

        if (!validarEmail(email)) {
            throw new IllegalArgumentException("E-mail inválido. O e-mail deve estar em um formato válido.");
        }

        if (!validarSenha(senha)) {
            throw new IllegalArgumentException(
                    "Senha inválida. Somente letras (a-z) e números, e no mínimo 8 caracteres.");
        }

    }

    // Valida o formato do email
    private boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(regex);
    }

    // Valida a senha
    private boolean validarSenha(String senha) {
        String regex = "^[A-Za-z0-9]{8,}$"; // Pelo menos 8 caracteres, somente letras e números
        return senha != null && senha.matches(regex);
    }
}
