<?php
declare(strict_types=1);

namespace Shop\User\Model;

use Illuminate\Auth\Authenticatable;
use Illuminate\Contracts\Auth\Authenticatable as AuthenticatableContract;
use Illuminate\Notifications\Notifiable;
use Pandawa\Component\Ddd\AbstractModel;
use Pandawa\Module\Api\Security\Contract\SignableUserInterface;
use Shop\User\Relation\Role\BelongsToRole;
use Shop\User\Relation\Role\HasOneRole;

/**
 * @author  Iqbal Maulana <iq.bluejack@gmail.com>
 */
class User extends AbstractModel implements AuthenticatableContract, SignableUserInterface
{
    use Authenticatable, BelongsToRole;

    /**
     * @var array
     */
    protected $fillable = [
        'username',
        'email',
        'name',
        'email',
        'phone',
        'gender',
        'role_id',
        'password'
    ];

    protected $hidden = [
        'password'
    ];

    protected function setPasswordAttribute($value)
    {
        $this->attributes['password'] = bcrypt($value);
    }

    public function getSignPayload(): array
    {
        return [
            'sub' => $this->id,
            'username' => $this->username,
        ];
    }
}
