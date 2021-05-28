<#import 'spring.ftl' as spring>
<#import 'template.ftlh' as template/>

<@template.template 'Admin View | BlaBlaFly'>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Id</th>
                <th scope="col">Username</th>
                <th scope="col">Roles</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
            <#list allUsers as account>
                <tr>
                    <th scope="row">${account_index + 1}</th>
                    <td>${account.id}</td>
                    <td>${account.username}</td>
                    <td>
                        <#list account.roles as role>
                            <span>${role.name}; </span>
                        </#list>
                    </td>
                    <td>
                        <form action="<@spring.url "/admin"/>" method="post" modelattribute="deleteUser">
                            <input type="hidden" name="userId" value="${account.id}">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
</@template.template>
