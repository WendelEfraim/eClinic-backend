package br.com.eClinic.chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.eClinic.modelo.medico.Medico;
import br.com.eClinic.modelo.paciente.Paciente;

public class ChatbotLoginValidar {
    // Mapa para armazenar 
    private static final Map<String, Medico> medicos = new HashMap<>();
    private static final Map<String, Paciente> pacientes = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        System.out.println("Bem-vindo ao eClinic+\n" + 
                        "Estamos felizes por você escolher nossa plataforma para cuidar da sua saúde. Para começar, você pode selecionar uma das opções abaixo:\n" + 
                        " 1. Cadastrar-se\n"  + 
                        " 2. Fazer login\n" + 
                        " 3. login de Administrado ou Médico\n" +
                        " 4. Falar com o Suporte\n" +
                        " 5. Recuperar senha \n" + 
                        "Assim que você escolher uma opção, podemos dar continuidade à sua jornada conosco. Qual opção você gostaria de seguir? Equipe eClinic+");

        //criar uma constante para guardar a mensagem do usuário (opções)
        System.out.print("Aguarde um momento...");
        System.out.print("Por favor, digite seu email: ");
        String usuarioInput = scanner.nextLine(); 

        System.out.print("Agora, digite sua senha: ");
        String senhaInput = scanner.nextLine(); 

        // Tenta validar login
        Medico medicoLogado = validarLoginMedico(usuarioInput, senhaInput);
        Paciente pacienteLogado = validarLoginPaciente(usuarioInput, senhaInput);

    
        if (medicoLogado != null) {
            System.out.println("Login bem-sucedido, Dr. " + medicoLogado.getNomeCompleto() + "!");
            medicoLogado.exibirMenu(); // Exibe o menu do médico
        } else if (pacienteLogado != null) {
            System.out.println("Login bem-sucedido, " + pacienteLogado.getNomeCompleto() + "!");
            pacienteLogado.exibirMenu(); // Exibe o menu do paciente
        } else {
            System.out.println("Usuário ou senha inválidos. Tente novamente.");
        }

        scanner.close();
    }

    public static Medico validarLoginMedico(String usuarioInput, String senhaInput) {
        // Verifica se o email e a senha do médico é válido
        Medico medico = medicos.get(usuarioInput);
        if (medico != null && medico.getSenha().equals(senhaInput)) {
            return medico; 
        }
        return null; // Retorna null caso não encontre o médico ou a senha esteja incorreta
    }

    
    public static Paciente validarLoginPaciente(String usuarioInput, String senhaInput) {
        // Verifica se o email e a senha do paciente é válido
        Paciente paciente = pacientes.get(usuarioInput);
        if (paciente != null && paciente.getSenha().equals(senhaInput)) {
            return paciente; 
        }
        return null; // Retorna null caso não encontre o paciente ou a senha esteja incorreta
    }
}