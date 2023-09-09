<hr>
<h1>SpringBoot, Angular fullstack-project</h1>
Technologies: Angular, SpringBoot, MySQL
</br>
<hr>
<h3>Guide to run SpringBoot App:</h3>
<ul>
	<li>Go to: <strong>SpringBoot-Angular-Project/backend/</strong></li>
	<p>Run code:
		<ol>
			<li><code style="user-select: all;">mvn clean install spring-boot:run</code></li>
		</ol>
		</p>
		<li>Access Database: <a href="http://localhost:8080/h2-console">http://localhost:8080/h2-console</a> with JDBC URL as <code>jdbc:h2:mem:teams_management_system</code>, <code>sa</code> as the username and <code> </code> (space) as the password</li>
		<li>Access ER Diagram & Postman Collection: <a href="https://github.com/sahilparekh1212/SpringBoot-Angular-Project/tree/main/etc" target="_blank">/etc</a></li>
</ul>
</br>
<hr>
<h3>Guide to run Angular App:</h3>
<ul>
	<li>Go to: <strong>fullstack-project/frontend/</strong></li>
	<br>Run code:
		<ol>
			<li><code style="user-select: all;">npm start</code></li>
			<li><code>Login to <a>http://localhost:4200</a> using username of user/admin/combo with password same as the username.</code></li>
		</ol>
</ul>
</br>
<hr>
<h3>Todo:</h3>
<ol>
	<li>Backend: Add relevant response code</li>
	<li>Backend: Add test cases for remaining APIs and Controllers</li>
	<li>UI: Implement Refresh Token :e.g. in last 5 min of 30 min session</li>
</ol>
</br>
<hr>
