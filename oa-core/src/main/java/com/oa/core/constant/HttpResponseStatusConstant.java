package com.oa.core.constant;

/**
 * http响应状态常量
 *
 * Created by [张渊]
 * 2017/12/6 16:12
 */
public interface HttpResponseStatusConstant {

    /**
     * 1xx（临时响应）
     */
    int CONTINUE = 100;                 // （继续） 初始的请求已经接受，客户应当继续发送请求的其余部分。（HTTP 1.1新）

    int SWITCHING_PROTOCOLS = 101;      // （切换协议） 服务器将遵从客户的请求转换到另外一种协议（HTTP 1.1新）

    /**
     * 2xx （成功）表示成功处理了请求的状态代码。
     */
    int OK = 200;                       // （成功） 一切正常，对GET和POST请求的应答文档跟在后面。如果不用setStatus设置状态代码，Servlet默认使用202状态代码。

    int CREATED = 201;                  // （已创建） 服务器已经创建了文档，Location头给出了它的URL。

    int ACCEPTED = 202;                 // （已接受） 已经接受请求，但处理尚未完成。

    int NON_AUTHORITATIVE = 203;        // （非授权信息） Information 文档已经正常地返回，但一些应答头可能不正确，因为使用的是文档的拷贝（HTTP 1.1新）。

    int NO_CONTENT = 204;               // （无内容） 没有新文档，浏览器应该继续显示原来的文档。如果用户定期地刷新页面，而Servlet可以确定用户文档足够新，
                                        //           这个状态代码是很有用的。

    int RESET_CONTENT = 205;            // （重置内容） 没有新的内容，但浏览器应该重置它所显示的内容。用来强制浏览器清除表单输入内容（HTTP 1.1新）。

    int PARTIAL_CONTENT = 206;          // （部分内容） 客户发送了一个带有Range头的GET请求，服务器完成了它（HTTP 1.1新）。

    /**
     * 3xx （重定向）表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向。
     */
    int MULTIPLE_CHOICES = 300;         // （多种选择） 客户请求的文档可以在多个位置找到，这些位置已经在返回的文档内列出。如果服务器要提出优先选择，则应该在Location应答头指明。

    int MOVED_PERMANENTLY = 301;        // （永久移动） 客户请求的文档在其他地方，新的URL在Location头中给出，浏览器应该自动地访问新的URL。

    int FOUND = 302;                    // （临时移动） 类似于301，但新的URL应该被视为临时性的替代，而不是永久性的。注意，在HTTP1.0中对应的状态信息是“Moved Temporatily”，
                                        //            而HttpServletResponse中相应的常量是SC_MOVED_TEMPORARILY，而不是SC_FOUND。

    int SEE_OTHER = 303;                // （查看其他位置） 类似于301/302，不同之处在于，如果原来的请求是POST，Location头指定的重定向目标文档应该通过GET提取（HTTP 1.1新）。

    int NOT_MODIFIED = 304;             // （未修改） 客户端有缓冲的文档并发出了一个条件性的请求（一般是提供If-Modified-Since头表示客户只想比指定日期更新的文档）。
                                        //           服务器告诉客户，原来缓冲的文档还可以继续使用。

    int USE_PROXY = 305;                // （使用代理） 客户请求的文档应该通过Location头所指明的代理服务器提取（HTTP 1.1新）。

    int TEMPORARY_REDIRECT = 307;       // （临时重定向） 和302（Found）相同。许多浏览器会错误地响应302应答进行重定向，即使原来的请求是POST，即使它实际上只能在POST请求的应答
                                        //              是303时才能重定向。由于这个原因，HTTP 1.1新增了307，以便更加清除地区分几个状态代码：当出现303应答时，浏览器可以跟随
                                        //              重定向的GET和POST请求；如果是307应答，则浏览器只能跟随对GET请求的重定向。注意，HttpServletResponse中没有为该状态
                                        //              代码提供相应的常量。（HTTP 1.1新）


    /**
     * 4xx（请求错误）这些状态代码表示请求可能出错，妨碍了服务器的处理。
     */
    int BAD_REQUEST = 400;              // （错误请求） 请求出现语法错误。

    int UNAUTHORIZED = 401;             // （未授权） 客户试图未经授权访问受密码保护的页面。应答中会包含一个WWW-Authenticate头，浏览器据此显示用户名字/密码对话框
                                        //           ，然后在填写合适的Authorization头后再次发出请求。

    int FORBIDDEN = 403;                // （禁止） 资源不可用。服务器理解客户的请求，但拒绝处理它。通常由于服务器上文件或目录的权限设置导致。

    int NOT_FOUND = 404;                // （未找到） 无法找到指定位置的资源。这也是一个常用的应答，HttpServletResponse专门提供了相应的方法：sendError(message)。

    int METHOD_NOT_ALLOWED = 405;       // （方法禁用） 请求方法（GET、POST、HEAD、DELETE、PUT、TRACE等）对指定的资源不适用。（HTTP 1.1新）

    int NOT_ACCEPTABLE = 406;           // （不接受） 指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容（HTTP 1.1新）。

    int PROXY_AUTHENTICATION_REQUIRED = 407; // （需要代理授权） 类似于401，表示客户必须先经过代理服务器的授权。（HTTP 1.1新）

    int REQUEST_TIMEOUT = 408;          // （请求超时） 在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。（HTTP 1.1新）

    int CONFLICT = 409;                 // （冲突） 通常和PUT请求有关。由于请求和资源的当前状态相冲突，因此请求不能成功。（HTTP 1.1新）

    int GONE = 410;                     // （已删除） 所请求的文档已经不再可用，而且服务器不知道应该重定向到哪一个地址。它和404的不同在于，返回407表示文档永久地离开了
                                        //           指定的位置，而404表示由于未知的原因文档不可用。（HTTP 1.1新）

    int LENGTH_REQUIRED = 411;          // （需要有效长度） 服务器不能处理请求，除非客户发送一个Content-Length头。（HTTP 1.1新）

    int PRECONDITION_FAILED = 412;      // （未满足前提条件） 请求头中指定的一些前提条件失败（HTTP 1.1新）。

    int REQUEST_ENTITY_TOO_LARGE = 413; // （请求实体过大） 目标文档的大小超过服务器当前愿意处理的大小。如果服务器认为自己能够稍后再处理该请求，则应该提供一个Retry-After头（HTTP 1.1新）。

    int REQUEST_URI_TOO_LONG = 414;     // （请求的 URI 过长） URI太长（HTTP 1.1新）。

    int REQUESTED_RANGE_NOT_SATISFIABLE = 416; // 服务器不能满足客户在请求中指定的Range头。（HTTP 1.1新）


    /**
     * 5xx（服务器错误）这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
     */
    int INTERNAL_SERVER_ERROR = 500;    // （服务器内部错误） 服务器遇到了意料不到的情况，不能完成客户的请求。

    int NOT_IMPLEMENTED = 501;          // （尚未实施） 服务器不支持实现请求所需要的功能。例如，客户发出了一个服务器不支持的PUT请求。

    int BAD_GATEWAY = 502;              // （错误网关） 服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答。

    int SERVICE_UNAVAILABLE = 503;      // （服务不可用） 服务器由于维护或者负载过重未能应答。例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个Retry-After头。

    int GATEWAY_TIMEOUT = 504;          // （网关超时） 由作为代理或网关的服务器使用，表示不能及时地从远程服务器获得应答。（HTTP 1.1新）

    int HTTP_VERSION_NOT_SUPPORT = 505; // （HTTP 版本不受支持） 服务器不支持请求中所用的 HTTP 协议版本。


}
