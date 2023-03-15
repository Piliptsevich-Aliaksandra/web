<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <h1>Blog Page</h1>
        <#list allPosts as post>
            <@c.post post=post/>
        </#list>
    </div>
</@c.page>