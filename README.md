# Challenge de App sobre listagem de empresas.
Esse projeto consiste numa aplicação desenvolvida em Kotlin para a plataforma Android cujo objetivo é consumir uma API Rest e listar as empresas que a API possui.

# Dependências


- [*Material*](https://material.io/develop/android) - Escolhida para usar os Material components oficiais da Google.
- [*Coil*](https://github.com/coil-kt/coil) - Utilizada para o carregamento e cache das imagens, é mais leve que o Glide e as suas extensões facilitam seu uso com as views utilizadas no projeto.
- [*GSON*](https://github.com/google/gson) - Lib usada para converter Json em Dados.
- [*Kotlin Coroutines*](https://github.com/Kotlin/kotlinx.coroutines) - Utilizada para a execução de tarefas assíncronas, é facil de utilizar, simples de manusear utilizando Kotlin e a considero mais simples e poderosa que RxJava.
- [*Retrofit*](https://square.github.io/retrofit/) - Lib usada para fazer as requisições da API Rest.
- [*Koin*](https://github.com/InsertKoinIO/koin) - Escolhi Koin para ser usado como injetor de dependencias por achar que ele se integra melhor ao Kotlin do que o Dagger2, o que o torna uma ferramenta mais simples e poderosa de ser usada em conjunto ao Kotlin.
- [*Android KTX*](https://developer.android.com/kotlin/ktx?gclid=CjwKCAjwiOv7BRBREiwAXHbv3EwiZJSsgXNRA1cZ5BtavjrAjofzIeDe6UGwGwm3Hk9XDFqp55eKZhoCrcAQAvD_BwE&gclsrc=aw.ds) - Extensões Kotlin disponibilizadas oficialmente pela Google.

#  Com mais tempo eu faria:

Com mais tempo eu faria um refatoramento na arquitetura geral do projeto, tornando-a mais clean. Implementaria Room Database para salvar as informações, poupando requisições da API sempre que reinicia a aplicação. Implementaria testes unitários e testes de UI. 

# Funcionamento

 Abra o diretório `test_ioasys` no Android Studio ou execute o apk `test_ioasys.apk` contido na raiz do repositório.

![gif](enterprises.gif)

