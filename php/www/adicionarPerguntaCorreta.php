<html>
<head>
<title>Inserir novo Perfil</title>
<link rel='stylesheet' href='style.css'/>
<head>
<body>
<?php 
include 'functions.php';
include 'header.php';
$pdo = create_database_connection();
?>
<div class='container'>
	<?php
		$message="";
		if (isset($_POST['submit'])) {
			if((!empty($_POST['texto']))){
				$QuestionarioID=$_GET['questionario'];
				$PerguntaID=$_GET['pergunta'];
				$id=0;
				$message="";
				$correta=1;
				$statement=$pdo->prepare("INSERT INTO resposta VALUES(:id,:texto,:correta,:PerguntaID)");
				$statement->bindParam(':id',$id);
				$statement->bindParam(':texto',$_POST['texto']);
				$statement->bindParam(':correta',$correta);
				$statement->bindParam(':PerguntaID',$PerguntaID);
				$statement->execute();
				header('location: adicionarPerguntaOutras.php?questionario='.$QuestionarioID.'&pergunta='.$PerguntaID);
			}else{
				$message="Por favor preencha todos os campos";
			}
		}
		if($message!=''){
			echo"<div class='box'>$message</div>";
		}

		?>
		<html>
		<body>

		<form action="" method="post">
		Introduza a resposta correta a pergunta:<br/>
		<input type="text" name="texto">
		<br/><br/>
		<input type='submit' name='submit' value='Inserir'>
		</form>

		</body>
		</html>
</div>
</body>
</html>