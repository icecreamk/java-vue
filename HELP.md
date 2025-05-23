# Getting Started

### docker db
> sudo docker run --name mysql-wiki -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8

### websocket

```vue
<script lang="ts">
  import { defineComponent, onMounted } from 'vue';
  import { notification } from 'ant-design-vue';

  export default defineComponent({
    name: 'test-websocket',
    setup() {

      let websocket: any;
      let token: any;
      const onOpen = () => {
        console.log('WebSocket连接成功，状态码：', websocket.readyState)
      };
      const onMessage = (event: any) => {
        console.log('WebSocket收到消息：', event.data);
        notification['info']({
          message: '收到消息',
          description: event.data,
        });
      };
      const onError = () => {
        console.log('WebSocket连接错误，状态码：', websocket.readyState)
      };
      const onClose = () => {
        console.log('WebSocket连接关闭，状态码：', websocket.readyState)
      };
      const initWebSocket = () => {
        // 连接成功
        websocket.onopen = onOpen;
        // 收到消息的回调
        websocket.onmessage = onMessage;
        // 连接错误
        websocket.onerror = onError;
        // 连接关闭的回调
        websocket.onclose = onClose;
      };

      onMounted(() => {
        // WebSocket
        if ('WebSocket' in window) {
          token = String(Math.random())
          // 连接地址：ws://127.0.0.1:8880/ws/xxx
          websocket = new WebSocket('ws://127.0.0.1:888' + '/ws/' + token);
          initWebSocket()
          // 关闭
          // websocket.close();
        } else {
          alert('当前浏览器 不支持')
        }
      });

      return {
      }
    }
  });
</script>


```


##### mq
发mq->收mq->推送ws消息->弹出消息