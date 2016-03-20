<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>
    <h2>Create a free Spitter account</h2>

    <sf:form method="POST" modelAttribute="spitter"
            enctype="multipart/form-data"><!--enctype allows to send images
            each field would be sent as a particular part of POST request-->
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><label for="username">Username:</label></th>
                    <td>
                        <sf:input path="username" size="15" maxlength="15" id="username" />
                        <sf:errors path="username" cssClass="error"/>
                    </td>
                </tr>
                <tr>
                    <th><label for="password">Password:</label></th>
                    <td><sf:password path="password" size="30"
                                     showPassword="true" id="password"/>
                        <sf:errors path="password" cssClass="error"/>
                    </td>
                </tr>

                <tr>
                    <th><label for="email">Email Address:</label></th>
                    <td>
                        <sf:input path="email" size="30" id="email"/>
                        <sf:errors path="email" cssClass="error"/>
                    </td>
                </tr>
                <!--<start id="image_field"/>-->
                <tr>
                    <th><label for="image">Profile image:</label></th>
                    <td><input name="image" type="file" id="image"/>
                </tr>
                <!--<end id="image_field"/>-->
                <tr>
                    <th></th>
                    <td>
                        <sf:checkbox path="updateByEmail" id="updateByEmail"/>
                        <label for="updateByEmail"
                                >Send me email updates!</label>

                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit"
                               value="I accept. Create my account." /></td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
</div>
