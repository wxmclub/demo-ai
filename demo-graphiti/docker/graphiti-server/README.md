# graphiti-server启动说明

```bash
# 构建镜像-指定embedding模型
docker build -t my/graphiti:0.10.5 .
```

```bash
# 复制env
cp .env.example .env
# 修改env中的 OPENAI_API_KEY

# 启动
docker compose up -d
# 停止
docker compose down
```

* neo4j数据库地址: http://localhost:17474/browser/
* 接口地址: http://localhost:8000/docs
