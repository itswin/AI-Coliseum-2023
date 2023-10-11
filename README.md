# AI-Coliseum-2023
1 Musketeer's code for AIC2023. I placed first!

# Game Replay

https://github.com/itswin/AI-Coliseum-2023/assets/15388838/05510c06-2a03-4aee-84b1-dffc4bf21abd

# Some strategy highlights
- Explore more aggressively before you've seen an enemy.
- Ignore bases early and prioritize economy for more units. (Results in losing on maps that are not interactive)
- Armed pitchers look for ball placements such that it can schedule an ally to bat it into an enemy immediately.
  - Only the direct cardinal direction is considered as a batting direction for bytecode reasons.
  - The pitcher requests the HQ to schedule them early for next turn if it needs to move before an eligible batter.
- Batters boost ally batters closer towards enemies if the batted ally can move.
- Map in shared array to deduce symmetry and enhance exploration. 1,000,000 integers is CRAZY.
- Units send heartbeats to resources to avoid overload.
