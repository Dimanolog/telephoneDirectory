<div id="header">
    <h2>User list </h2>
</div>


<div id="content">

    <table class="userTable">
        <tr>
            <th>User</th>
            <th>Telephone Number</th>
            <th>Telephone Company</th>
        </tr>
            <#list userList as user>
                <tr>
                    <td>${user.firstName + " " + user.lastName}</td>
                    <td>${user.telephoneNumber.telephoneNumber}</td>
                    <td>${user.telephoneNumber.telephoneCompany.name}</td>
                </tr>
            </#list>
    </table>

    <form name="uploadingForm" enctype="multipart/form-data" action="/userList/uploadUserFile" method="POST">
        <p>
            <input id="fileInput" type="file" name="file">
        </p>
        <p>
            <input type="submit" value="Upload file">
        </p>
    </form>

    <form method="get" action="/userList/userPdf">
        <button type="submit">Download user pdf</button>
    </form>

</div>