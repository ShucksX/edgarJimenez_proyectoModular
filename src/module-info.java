module ProyectoModular_JimenezAcevesEdgar {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.net.http;
	requires org.apache.opennlp.tools;
	
	opens application to javafx.graphics, javafx.fxml;
}
