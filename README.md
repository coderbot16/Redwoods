# Redwoods

![Redwoods Banner](showcase/Redwood%20Forest%20Canyon%20Banner.png)
*Seed: -7966525194701114253 XYZ: 3366/82/-122*

## Introduction

Redwoods is a mod for Minecraft that adds several biomes and tree types to the game themed around massive, trees. Have
you wanted to traverse a massive forest of conifer trees, totally in awe at the beauty of your landscape? Then Redwoods 
is the mod for you.

Redwoods, despite the name, adds both redwood and fir trees to the game. This mod is intended to be used alongside the
Traverse, as a way to replace their Coniferous Forest biomes with ones that are closer to classic ExtrabiomesXL. Traverse
does an amazing job on all its biomes, but in my opinion it just feels like that truly massive trees are missing. Thus,
I made this mod to act as a companion mod for Traverse. While Traverse is not required, it is highly recommended!

## Features

Redwoods adds two types of large conifer trees:
 * Redwood: A deep red-brown wood with lush green leaves.
 * Fir: A brown wood containing hints of green with lush green leaves.

There are two variants of redwood and fir trees, both with very large sizes:

 * 1x1: "Normal" - A large tree that is 24-32 blocks tall, with a maximum size of 13x13 at the base.
 * 2x2: "Mega" - A positively gargantuan tree that can be 32-48 blocks tall, with a maximum size of 16x16 at the base.
   This is the absolute largest a tree can be horizontally without causing cascading world generation.

The 2x2 variants of conifer trees also use special "quarter" logs which are designed to slot together to form a massive,
contiguous log with rings on the interior. Both variants of trees generate leaves in a stacked cone pattern, reminiscent
of stereotypical christmas trees.

There are 4 biomes added by this mod, each containing an assortment of conifer trees:
 * Redwood Forest: A forest containing massive, towering Redwood trees, situated on a mountainous but somewhat flat plateau.
 * Lush Redwood Forest: Like the Redwood Forest, but also featuring 1x1 Fir trees alongside the redwood trees.
 * Temperate Rainforest: Modeled after Vancouver, this luscious forest contains both normal and massive fir trees throughout the 
   biome. It is very mountainous, but also has large variation in height making it both challenging and rewarding to explore.
 * Snowy Rainforest: Exactly like the Temperate Rainforest, but with snow and a much colder biome color. Still very lush.

## Technical notes

There are many technical hurdles in efficiently implementing such massive trees, especially due to differences between
1.7.10 and 1.12.2 in rendering. Even back in 1.7, EBXL conifer forests were borderline unplayable without fast graphics.

By default, for rendering Conifer leaves Redwoods utilizes an optimization originally found in 
[OptiLeaves](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1289639-v1-4-optileaves-faster-leaves-rendering-looks)
to provide a halfway point between Fast Graphics and Fancy Graphics. The optimization is to cull inner faces of leaf
blocks, like in Fast Graphics, but use the transparent leaf texture, like in Fancy Graphics. This allows you to have
far higher FPS while in forests, and in fact for these massive trees it actually looks very good. Of course, it can be
disabled in the config, but that is not recommended. On that note, if you enable fast graphics with this option on, 
you'll get the ugly textures like normal.

In addition, by default, Conifer leaves don't diffuse light. While this is also configurable, enabling light diffusing
is not recommended either because it causes mob spawns during the day, and also causes extreme lag. Generating a spawn
within a Redwoods biome takes 5 seconds without light diffusing, and 40 seconds with light diffusing. With light diffusing
enabled, there is also extreme worldgen lag in general, with the exploration of a single player dropping TPS to 4. So,
keep your TPS high, and let leaves pass light through.

TL;DR Big trees are hard, and Redwoods has to employ several optimizations to make the game playable. You can disable
them, but you shouldn't.

## Licensing

The code in this mod has similar function to ExtrabiomesXL, but is completely written from scratch to work with newer
Minecraft versions.

You are totally free to redistribute this mod (including in modpacks), with the sole restriction being that you credit me. 
I am of the belief that overly restrictive mod licenses are an evil that has no place in our collaborative modding 
community, so therefore this mod is licensed under the MIT license.

The textures have a separate license (CC BY-SA 3.0), as they are derived from ExtrabiomesXL as permitted by that license. 
More can be found in [LICENSE-TEXTURES](LICENSE-TEXTURES.md).

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