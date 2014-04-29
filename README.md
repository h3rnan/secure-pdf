secure-pdf
==========

Provides a secure password to a PDF through flex component.

<h4>Required:</h4>
<ul>
  <li>Compile "pass-rest-flex" project and paste in resource folder
  <li>Java 1.7
  <li>Database Oracle XE
  <li>Run script db/schema.sql
</ul>

<h4>Use</h4>
<b>Spanish:</b> El projecto compone de 2 partes:
<ol>
  <li>Servicio de ver clave: Otorga el permiso de ver la clave en el PDF, ete servicio está en la clase "ControlClaveImpl".
  <li>Generación de PDF: Se realiza por medio de la clase "GeneradorPdfImpl". La generación del documento tiene opciones para configurar su nivel de seguridad.
</ol>

<h4>Note:</h4>
- Incomplete load properties, I leave it to suit the interested.
- Missing test.

