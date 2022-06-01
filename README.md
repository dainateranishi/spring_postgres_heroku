# introduction

このリポジトリは Spring で作成したアプリケーションを Heroku へデプロイするためのサンプルアプリです．

このリポジトリをクローンして手順に沿ってデプロイすれば，動く(?)と思います． ただ勉強記録用でもあるので，Spring のプロジェクトを作成するところから説明します． 必要のない部分は適宜読み飛ばしてください．

# Requirement

実行環境を記載します．

- masOS Monterey 12.1
- Spring Tool Suite4 4.14
- Java 11
- Lombok
- Heroku CLI 7.60.2

# Create Spring Project

まずは Spring Tool Suite (STS) 上でプロジェクトを作成します．

STS を起動し command + N から wizard を起動して，検索バーに spring と入力します． 絞り込めた後に Spring Starter Project を選択します．

![create_project](https://user-images.githubusercontent.com/49631708/171402284-8811c1c7-d45f-4cca-9bdf-c1bd1d5409e0.png)

次にプロジェクト名を決定します. 今回作成するプロジェクタは Springbot という名前にします.

また Packege 名は com にします.

![project_name](https://user-images.githubusercontent.com/49631708/171403977-8148963a-1724-487f-8aeb-0717c0fd493c.png)

最後に Dependency を選択します． Spring Web, Thymeleaf, Lombok を選択します。

Finish を押したらプロジェクト作成の完成です.

![dependency](https://user-images.githubusercontent.com/49631708/171402814-5a1e36e5-7e96-497b-8bc4-ab07c91f71d3.png)

# Cording

では、プロジェクトの中身を作成していきます.

src/main/java を選択し、command + N を押して wizard を起動します。検索バーに package と入力し package を選択します.

作成するプロジェクト名は sample とします。

![create_packege](https://user-images.githubusercontent.com/49631708/171405041-9680af3d-04e9-48dd-8b51-80d9356852b3.png)

com.sample を選択し、同じ方法で wizard を起動します。検索バーに class と入力し、SampleController を作成します.

![create_samplecontroller](https://user-images.githubusercontent.com/49631708/171405363-2493a242-9a4e-4a59-8d00-5c1a3fab85d6.png)

以下のように記述し、保存します.

```
package com.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleConteroller {

	@GetMapping("/")
	public String getIndex(Model model) {
		return "sample/helloWorld";
	}

}
```

次に HTML ファイルを作成します。src/main/resources/ をクリックし、中にある templates を選択して wizerd を起動します。Folder を検索し、sample という名前のフォルダを作成します。

作成したら template/sample を選択し、再び wizard を起動します。HTML を検索し、helloWorld.html を作成します。その後、以下のように記述し、保存します。

```
<!DOCTYPE html>
<html xmlns:th="http://www.themeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>Sample</title>
</head>
<body>
	<h1>Hello World!</h1>
</body>
</html>

```

ここまでできたら一旦はこーかるで動きます。プロジェクトを選択しながら右クリックし、Run as -> Spring boot app を選択して、アプリを起動します。

ブラウザを起動し、localhost:8080/sample/ と入力します。以下のような画面が表示されれば成功です。

![project_run](https://user-images.githubusercontent.com/49631708/171407523-ccb69642-1ebe-425b-aa27-a3f9b934b229.png)

# Deploy to Heroku

完成したプロジェクトを Heroku へデプロイしていきます。

まずは Terminal を起動し、Springbot まで移動します。

```
~ $ cd /[Spring-work-spaceまでのパス]/Springbot
~ SpringBot $
```

このプロジェクト内でリポジトリを新規作成します。その後、これまで作成したファイルを commit しておきます。

```
~ SpringBot $ git init
~ SpringBot $ git add .
~ SpringBot $ git commit -m "first commit"
```

次に Heroku へログインをします。Terminal で 以下のコマンドを打ち、Enter を押します。ブラウザが起動し、ログイン画面が表示されるのでログインします。色々認証がありますが、頑張ってログインします。

```
~ SpringBot $ heroku login
heroku: Press any key to open up the browser to login or q to exit:
```

ログインが完了したら、Heroku 上でアプリケーションを作成します。アプリ名は使用されていない名前を用いる必要があるので、springbot-[任意の文字列]で、他と被らないようにしておきましょう。また大文字は使えないので小文字で設定しましょう。

```
~ SpringBot $ heroku create -a springbot-[任意の文字列]
```

プロジェクト内でこのコマンドを打つことが重要であり、そうすることで git の リモートリポジトリが自動で設定されます。以下のコマンドで確認してみてください。

```
~ SpringBot $ git remote -v
heroku	https://git.heroku.com/springbot-[任意の文字列].git (fetch)
heroku	https://git.heroku.com/springbot-[任意の文字列].git (push)
```

Heroku へのデプロイはこのリモートリポジトリへ push すると自動で起動してくれます。

```
~ SpringBot $ git push heroku main
```

今の状態で push すると軌道が失敗したというエラーが出ると思います。STS へ戻り、このプロジェクト内に 2 つの設定ファイルを追加すればこのエラーがなくなり、正しくデプロイできるようになります。

Springbot を選択し、wizard を起動します。File を 選択し、ProcFile という名前のファイルを作成します。拡張子は必要ありません。

![create_procfile](https://user-images.githubusercontent.com/49631708/171414837-87aa2ded-30bb-4723-bb97-1a3af19222d7.png)

ProcFile 内に以下の行を追加して、保存してください。

```
web: java -Dserver.port=$PORT -jar target/Springbot-0.0.1-SNAPSHOT.jar
```

target/Springbot-0.0.1-SNAPSHOT.jar 　の　 Springbot の部分はプロジェクト名ですが正確には pom.xml 内の以下の部分と一致させる必要があります。

![pom.xml](https://user-images.githubusercontent.com/49631708/171416886-eec3b4d6-9fd3-4584-aae7-712a85e24faf.png)

次に ProcFile と同じ階層に system.properties を作成します。作成方法は wizard -> File から作成します。

system.properties に以下の行を追加します。これは Java の　 version を指定する記述に思われます。

```
java.runtime.version=11
```

保存が完了したら Terminal へ戻り、今の変更を commit して push するとデプロイが完了します。

```
~ SpringBot $ git add .
~ SpringBot $ git commit -m "Procfileとsystem.propertiesの追加"
~ SpringBot $ git push heroku main
```

色々なログが流れ、以下のようなログが出力されると完了です。赤字で xxx と書かれている部分は Heroku でのアプリ名の[任意の文字列]の部分です。

![deploy_success](https://user-images.githubusercontent.com/49631708/171419241-6fc80578-4476-49b1-b986-3489e9759c4b.png)

この部分の deploy to Heroku と書かれている部分の右側の URL の後ろに /sample/ を追加してブラウザでアクセスすると、「Hello World」が表示されます。

```
https://springbot-xxx.herokuapp.com/sample/
```

これで Heroku へのデプロイは完了です。
