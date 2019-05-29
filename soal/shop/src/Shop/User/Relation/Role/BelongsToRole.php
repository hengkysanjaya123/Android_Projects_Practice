<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 18/12/18
 * Time: 02.33
 */

namespace Shop\User\Relation\Role;


use Pandawa\Component\Ddd\Relationship\BelongsTo;
use Pandawa\Component\Ddd\RelationshipBehaviorTrait;
use Shop\User\Model\Role;

trait BelongsToRole
{
    use RelationshipBehaviorTrait;

    public function role(): BelongsTo
    {
        return $this->belongsTo(Role::class);
    }

    public function hasRole(array $roles): bool
    {
        return in_array($this->role->name, $roles);
    }
}