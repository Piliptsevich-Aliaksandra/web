<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <h3>${userDetails.login}</h3>
        <div class="mb-1 text-muted">Registered ${userDetails.getFormattedRegistrationTime()}</div>
        <hr>
        <#if userDetails.aboutText??>
            <p>
            <div class="mb-1 text-muted">About me:</div>${userDetails.aboutText}
            <#if user?? && user.id == userDetails.id>
                <br><br><a href="/user/${user.id}/about/edit" class="btn btn-warning">Edit</a>
                <a href="/user/${user.id}/about/delete" class="btn btn-warning">Delete</a>
                <br><br>
            </#if>
            </p>
        </#if>
        <a href="/user/${userDetails.id}/posts">Posts</a>
        |
        <a href="/user/${userDetails.id}/comments">Comments</a>
        <br><br>
    </div>
</@c.page>