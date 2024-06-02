```mermaid
flowchart LR
subgraph controller
direction TB
top1[controller] --> bottom1[dto]
end
subgraph usecase
direction TB
top2[usecase] --> bottom2[model]         
end
subgraph service
direction TB
top3[service] --> bottom3[domain]         
end
subgraph repository
direction TB
top4[repository] --> bottom4[mapper]         
end
subgraph repositoryImpl
direction TB
top5[repositoryImpl] --> bottom5[entity]
end

    controller --> usecase
    usecase --> service
    service --> repository
    repository --> repositoryImpl

    %% ^ These subgraphs are identical, except for the links to them:

    %% Link *to* subgraph1: subgraph1 direction is maintained
    
    %% Link *within* subgraph2:
    %% subgraph2 inherits the direction of the top-level graph (LR)
```

Here is a simple flow chart:

```mermaid
graph TD;
    A-->B;
    A-->C;
    B-->D;
    C-->D;
```