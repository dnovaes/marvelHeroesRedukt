# Marvel Heroes :dove: :us: 

Aplicativo Android que mostra todos os heróis da marvel. Todas as informações são extraídas em tempo real da api oficial da marvel:
> https://developer.marvel.com/docs

<p align="center">
  <img src="https://imgur.com/vaZbSBz.png" alt="Movie full screen description"/ height="500px">
</p>

## Arquitetura
Foi utilizado arquitetura reativa (redux/redukt) que funciona separando o código em middlewares, dispatchers (ações) e reducers, bem como atualizando o layout a partir das alterações ocorridas no App State.

Os principais componentes dessa arquitetura são:
### Action Creator + Dispatcher:

Cada mudança ou interação do usuário com o app tem uma ação, o dispatcher é responsável por ordenar as ações e encaminhado-las ao middleware ou reducer.

### Middleware:

Responsável pelo transito de dados assíncronos. Nesse projeto, usado para sincronizar os dados com o servidor e salvar-los no banco de dados. 

### Reducer:

Responsável por manter sempre atualizado os dados no App State, que é utilizado nas telas para mostrar as informações para o usuário. Nesse projeto, até o momento, os filmes.

### Listener:

Responsável por atualizar a tela caso ocorra alguma alteração no App State.

## Bibliotecas utilizadas

#### [Redukt](https://github.com/raulccabreu/redukt)
Biblioteca escrita em Kotlin que implementa a arquitetura redux no android.

#### [Retrofit](https://square.github.io/retrofit/)
Requisições HTTP Client para Android

#### [Gson](https://github.com/google/gson)
Biblioteca para converter Json para Kotlin e vice versa

#### [Anvil](http://trikita.co/anvil/)
Biblioteca para desenvolver layouts e views reativos para Android

#### [Glide](https://github.com/bumptech/glide)
Biblioteca para carregando e cacheamento de imagens remotas


## Preview

- Video
https://drive.google.com/file/d/1z3ucMYcRzWQFWClk3H3cgDxZ5WLoGIzc/view?usp=sharing

## APK:
https://drive.google.com/file/d/17WKZoCfLDS0rVS_3cyLUhYprPT1mcb53/view?usp=sharing


## Mais informações sobre Redux

|  |  |
| ------------- |:-------------:|
|![Redux1](https://community.theforeman.org/uploads/default/original/2X/5/53a41b947c614984b25fd350859a82f4a8600d05.png)|![Redux2](https://imgur.com/Es0p9gy.png)|

https://android.jlelse.eu/react-native-redux-architecture-part-1-8178fc9065c2

https://medium.com/@trikita/writing-a-todo-app-with-redux-on-android-5de31cfbdb4f

## Instruções para gerar o apk

1) Generate signing key
```
keytool -genkey -v -keystore marvelHeroesKey.keystore -alias marvelHeroesKey -keyalg RSA -keysize 2048 -validity 10000
```

2) Put generated file in `.../yourProjectFolder/app` and udpate `gradle.Properties` with information below:
```
KEY_STORE_FILE=marvelHeroesKey.keystore
KEY_ALIAS=marvelHeroesKey
KEY_STORE_PASSWORD=<The password you choose earlier with the keytool>
KEY_PASSWORD=<The password you choose earlier with the keytool>
```

3) Generate apk
```
./gradlew assembleRelease; find . -name *.apk
or
./gradlew assembleDebug; find . -name *.apk
```
