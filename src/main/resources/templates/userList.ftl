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
        <#list model["userList"] as user>
            <tr>
                <td>${user.firstName}</td>
                <td>${car.telephoneNumber.telephoneNumber}</td>
                <td>${car.telephoneNumber.telephoneCompany.name}</td>
            </tr>
        </#list>
    </table>
</div>