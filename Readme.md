## ApiStream - Kotlin 
Codificação de ApiStream para uma API RESTful desenvolvida em Kotlin usando o framework Ktor. A API é projetada para gerenciar podcasts, permitindo operações em registros de podcast. Com implementação de Kafka, DynamoDB, Kubernetes e
Workflows para CD e CI para integração continua e deploy e provisionamento de Infrastrutura com Terraform.

**Estrutura do Código**
O código é estruturado em pacotes, com o pacote com.ApiStream.controllers contendo a lógica do controlador para a API.

**PodcastController**
A classe PodcastController é o controlador principal da API. Ele usa uma instância do PodcastService para realizar operações de CRUD em registros de podcast.

A classe PodcastController define quatro métodos:

**getAll**: Este método é usado para obter todos os podcasts. Ele retorna uma lista de PodcastDto que contém informações sobre cada podcast.

**create**: Este método é usado para criar um novo podcast. Ele recebe um CreatePodcastDto que contém as informações necessárias para criar um novo podcast.

**update**: Este método é usado para atualizar um podcast existente. Ele recebe um CreatePodcastDto que contém as novas informações para o podcast e um id do podcast que deve ser atualizado.

**delete**: Este método é usado para excluir um podcast existente. Ele recebe um id do podcast que deve ser excluído.

**DTOs**
O código usa duas classes DTO (Data Transfer Object):

**PodcastDto**: Esta classe é usada para transferir informações sobre um podcast. Ela contém três campos: id, title e url.

**CreatePodcastDto**: Esta classe é usada para transferir as informações necessárias para criar um novo podcast. Ela contém dois campos: title e url.

**Model**
O código usa a classe Podcast como seu modelo principal. Esta classe representa um podcast e contém três campos: id, title e url.

**Serviço**
O PodcastService é usado para realizar operações de CRUD em registros de podcast. Ele é injetado no PodcastController e usado para realizar as operações necessárias.

**Como usar**
Para usar a API, você pode enviar solicitações HTTP para os endpoints correspondentes. Aqui estão alguns exemplos de como você pode fazer isso:

Para obter todos os podcasts, envie uma solicitação GET para /podcasts.

Para criar um novo podcast, envie uma solicitação POST para /podcasts com um corpo de solicitação que contém as informações do podcast.

Para atualizar um podcast existente, envie uma solicitação PUT para /podcasts/{id} com um corpo de solicitação que contém as novas informações do podcast.

Para excluir um podcast existente, envie uma solicitação DELETE para /podcasts/{id}.

Por favor, note que {id} deve ser substituído pelo id do podcast que você deseja atualizar ou excluir.

**Conclusão**
ApiStream é uma API uso de programação Kotlin, mas poderosa, para gerenciar podcasts. Ela é fácil de usar e altamente personalizável, tornando-a uma ótima escolha para qualquer projeto que requer gerenciamento de podcasts.

Autor:
**Emerson Amorim**