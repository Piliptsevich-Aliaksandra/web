<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5 mb-5">
        <h1>Sign up</h1>
        <form method="post">
            Login <input type="text" name="login" class="form-control" value="${userForm.login!}">
            <@c.error "userForm.login"/><br>
            Password <input type="password" name="password" class="form-control">
            <@c.error "userForm.password"/><br>
            <button type="submit" class="btn btn-success">Register</button>
        </form>
    </div>
</@c.page>