# Redwoods

![Redwoods Banner](showcase/Redwood%20Forest%20Canyon%20Banner.png)
*Seed: -7966525194701114253 XYZ: 3366/82/-122*

Redwoods, despite the name, adds both redwood and fir trees to the game. This is intended to be used alongside the mod
Traverse, as a way to replace their Coniferous Forest biomes with ones that are closer to classic ExtrabiomesXL. Traverse
does an amazing job on all its biomes, but in my opinion it just feels like that truly massive trees are missing. Thus,
I made this mod to act as a companion mod for Traverse. While Traverse is not required, it is highly recommended!

First off, Redwoods actually adds two types of trees:
 * Redwood: A deep red and brown colored wood with lush green leaves.
 * Fir: A somewhat greenish wood with lush green leaves.

Then, there are two variants of redwood and fir trees, both with very large sizes:

 * 1x1: "Normal" - A large tree that is 24-32 blocks tall, with a maximum size of 13x13 at the base.
 * 2x2: "Mega" - A positively gargantuan tree that can be 32-48 blocks tall, with a maximum size of 16x16 at the base.
   This is the absolute largest size a tree can be horizontally without causing cascading world generation.

The "Mega" variant of trees also use special "quarter" logs that are designed to slot together to form a massive,
contiguous log with rings on the interior. Both variants of trees generate leaves in a stacked cone pattern, reminiscent
of stereotypical christmas trees.

## Technical notes

There were many technical hurdles in efficiently implementing such massive trees, especially due to differences between
1.4.7/1.7.10 and 1.12.2 in rendering.

By default, for rendering Conifer leaves Redwoods utilizes an optimization originally found in 
[OptiLeaves](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1289639-v1-4-optileaves-faster-leaves-rendering-looks)
to provide a halfway point between Fast Graphics and Fancy Graphics. The optimization is to cull inner faces of leaf
blocks, like in Fast Graphics, but use the transparent leaf texture, like in Fancy Graphics. This allows you to have
far higher FPS while in forests, and in fact for these massive trees it actually looks very good. Of course, it can be
disabled in the config, if you don't like high FPS! :P

(Also, if you enable fast graphics, it you'll get the ugly textures like normal)

In addition, by default, Conifer leaves don't diffuse light. While this is also configurable, enabling light diffusing
is not at all recommended because it causes mob spawns during the day, and also causes extreme lag. Generating a spawn
within a Redwoods biome takes 5 seconds without light diffusing, and 40 seconds with light diffusing. With light diffusing
enabled, there is also extreme worldgen lag in general, with the exploration of a single player dropping TPS to 4. So,
keep your TPS high, and let leaves pass light through.

TL;DR Big trees are hard, and Redwood has to employ several optimizations to make the game playable.

#### Note on textures

The textures have a separate license (CC BY-SA 3.0), as they are derived from ExtrabiomesXL as permitted by that license. 
More can be found in LICENSE-TEXTURES.

## Screenshots

#### Border between a Snowy Rainforest (left) and a Temperate Rainforest (right)
![Temperate and Snowy Rainforest](showcase/Temperate%20and%20Snowy%20Rainforest.png)
*Seed: -3216449840978436673 XYZ: -5450/150/130*

#### Burned part of a Temperate Rainforest, revealing the inner log pattern
![Burned Temperate Rainforest](showcase/Burned%20Temperate%20Rainforest.png)
*Seed: -3216449840978436673 XYZ: -5485/105/212*

#### Inside of a Snowy Rainforest
![Snowy Rainforest](showcase/Snowy%20Rainforest.png)
*Seed: -3216449840978436673 XYZ: -5400/80/-27*

#### Interior of a Temperate Rainforest
![Temperate Rainforest](showcase/Temperate%20Rainforest.png)
*Seed: -3216449840978436673 XYZ: -5309/82/125*

#### Interior of a Temperate Rainforest (Another shot)
![Temperate Rainforest 2](showcase/Temperate%20Rainforest%202.png)
*Seed: -3216449840978436673 XYZ: -5336/88/134*

#### Interior of a Redwood Forest
![Redwood Fores](showcase/Redwood%20Forest.png)
*Seed: -7966525194701114253 XYZ: 3155/86/-132*

#### Beach of a Lush Redwood Forest
![Lush Redwood Forest Beach](showcase/Lush%20Redwood%20Forest%20Beach.png)
*Seed: -7966525194701114253 XYZ: 11463/87/-135*